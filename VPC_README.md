 # Virtual Private Cloud(VPC)
 - VPC is an isolated/private network used for securely hosting applications. Applications deployed in the VPC cannot be accessible to the external world unless it is configured to do so. We can customize the level of isolation we want with the help of VPC components like Subnets, ACLs (Access control lists).
 - For creating a VPC, you need to provide CIDR block as input which tells how many IP addresses you want for your VPC. For example, if you choose 10.0.0.0/24, it allocates 256 IP address for your usage, which is derived from the below formula.
   ```
       2^(32-block size) = No. of IP addresses 
       2^(32-24) = 2 ^ 8 = 256 IP addresses (Out of 256, some 4/5 IP addresses is used by AWS for internal purposes)
   ``` 
 - Let's consider, we have created a VPC, and we are allotted with 256 IP addresses, we can divide the 256 IPs into sub-networks(subnets) with equal partitions. Next point covers what is the need of dividing them into subnets. But, lets see how these IP addresses can be divided.
   - 10.0.0.0/24 can be divided into any no. of partitions we want. Let's consider we want to divide it into 4 partitions.
   - By applying the same formula we have used above, we can divide our VPC into 4 sub-networks of 64 block size
    ```
        10.0.0.0/26 = 2 ^ (32-26) = 64 IP addresses for each sub network. So, you can get  
            10.0.0.0/26    - subnet1
            10.0.0.64/26   - subnet2
            10.0.0.128/26  - subnet3
            10.0.0.192/26  - subnet4
        To give a quick explanation of the subnet partition,
        Subnet 1 block is represented as 10.0.0.0/26, which has 64 IP addresees, starting from 10.0.0.0 to 10.0.0.63. Subnet 2 block has also the same block size as subnet1's, starts from 10.0.0.64 and stops at 10.0.0.127, since 0 to 63 are used by subnet1.                       
   ```
  ## Subnets
 <div align="left">
      <img src="/VPC.png"></img>
   </div> 

  - The idea of dividing the VPC into subnets is to group them into public and private subnets and route the traffic to them accordingly. 
  - Private subnets does not allow public traffic. But, they can access the resources in the internet or other AWS resources in the same VPC. It uses NAT gateway for this. Nat gateway allows only one way communication, which is from VPC to internet, not vice versa. 
  - Public subnets allow public traffic by connecting to the internet gateway. Internet gateway is the component which sits at the top of your network stack, which allows internet traffic into your VPC.
  - Since, private subnets has one way communication, they are not exposed to internet, we can use them while creating EC2 instances for databases, server applications.
  - For applications which require internet facing, we will use public subnets while creating EC2 instance like web applications, application load balancers, etc.,

## Routing tables
 <div align="left">
      <img src="/ROUTETABLE.png"></img>
   </div> 

 - Routing the traffic into subnets is done with the help of routing tables. It controls what goes in and out of the subnets. 
 -  In the routing tables, we will be specifying what is the inbound and outbound traffic and what are the subnets associated. 
 - For public subnets, we will add a public route table and add Internet gateway as one of the routes and associate all the public subnets to this route table. Adding internet gateway to the routes makes public subnets accessible to the internet. 
 - For private subnets, we will add a private route table and add NAT gateway as one of the routes and associate all the private subnets to this route table. Adding NAT gateway block the public traffic into the private subnet.    
 - Subnets can be created across multiple zones in the region where VPC was created for higher availability.  
 - Communication between applications deployed in private subnets and the applications deployed in public subnets happens through security group configuration of EC2 instances which is out of the scope of VPC. 
 - Basically, you use route tables to create public and private subnets.
 
 ## ACL (Access control lists)
- ACL sits between routes and subnets in your VPC and blocks any unwanted traffic released by your route tables. 
- We can create custom rules in ACL to add additional protection to our subnets.  In each rule we will specify what traffic is allowed or denied. Thus, filtering what is accepted and denied by our VPC network.
- The routed traffic goes through these set of rules before hitting your subnets, If any of the rule is satisfied, it permits the traffic to your subnet. Otherwise, it denies.
  <div align="left">
      <img src="/VPC2.PNG"></img>
 </div>

##### At a high level, Routing tables and ACL helps in protecting what comes in and goes out of your VPC, it essentialy protects your network from being accessed by unknown resources. 

  <div align="left">
      <img src="/VPC1.PNG"></img>
 </div>
