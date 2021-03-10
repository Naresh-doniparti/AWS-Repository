 ## Virtual Private Cloud(VPC)
 - It is an isolated network used for hosting your applications, databases, etc., Applications deployed in the VPC cannot
   be accessible to the internet. 
 - We can customize the level of isolation we want in the VPC with the help of VPC components like Subnets, ACLs which will be 
   touched upon later.
 - For creating a VPC in AWS, you need to provide your CIDR block. For example, if you choose 10.0.0.0/24,  it allocates 
   256 IP address for our usage, which is derived from the below formula.
   ```
       2^(32-block size) = No. of IP addresses 
       2^(32-24) = 2 ^ 8 = 256 IP addresses (Out of 256, some 4/5 IP addresses is used by AWS for internal purposes)
   ``` 
 - Let's consider, we have created a VPC, and we are allotted with 256 IP addresses, we can divide the 256 Ips into sub-networks(subnets) 
   with equal partitions. 
   - 10.0.0.0/24 can be divided into any no. of partitions we want. Let's consider we want to divide it into 4 partitions.
   - By applying the same formula we have used above, we can divide our VPC into 4 sub-networks of 64 block size
    ```
        10.0.0.0/26 = 2 ^ (32-26) = 64 IP addresses for each sub network. So, you can get  
            10.0.0.0/26    - subnet1
            10.0.0.64/26   - subnet2
            10.0.0.128/26  - subnet3
            10.0.0.192/26  - subnet4
        To give a quick explanation of the subnet partition,
        Subnet 1 block is represented as 10.0.0.0/26, which has 64 IP addresees, starting from 10.0.0.0 to 10.0.0.63
        Subnet 2 block has also the same block size as subnet1's, starts from 10.0.0.64 and stops at 10.0.0.127, 
        since 0 to 63 are used by subnet1.                       
   ```
  ### Subnets 

   <div align="left">
      <img src="/VPC.jpg"></img>
   </div> 
 - The idea of dividing the VPC into subnets is to group them into public and private subnets and route the traffic to them accordingly.
 - Private subnets does not allow public traffic. But, they should access the resources in the internet using NAT gateway. 
   Nat gateway allows only one way communication, resources in private subnet can access public resources, but not vice versa. 
 - Public subnets allow public traffic. They use internet gateway to achieve this.
 - Routing to subnets is done with the help of routing tables. In the routing tables, we will be specifying what is the inbound
   and outbound traffic. We can associate multiple subnets to a route table.
   - For public subnets, a public route will be created, Internet gateway will be added as one of the routes .
   - For private subnets, a private route will be created, Nat Gateway will be added as one of the routes.
   
 - Since, private subnets are not exposed to internet, we can use them while creating secured resources like databases, server applications.
 - For applications which require internet facing, we will use public subnets while creating resources like EC2 web applications, application 
   load balancers, etc.,
 - Subnets can be created across zones in the region where VPC was created.
 
 ###ACL (Access control lists)
- It sits between routes and subnets and controls what comes in and out of the subnets. 
- Custom rule names has a naming convention, they start with rule name 100, when we add another rule, it has to be given 200(incremented by 100).
- In each rule we will specify what traffic is allowed or denied.
- The incoming traffic goes through these set of rules, if any of the rule is satisfied, it will be either permitted or denied based on the rule
   which it was evaluated against. If none of the rule gets evaluated, the traffic will not be permitted into subnet.
  <div align="left">
      <img src="/VPC2.PNG"></img>
 </div>

### Bigger Picture
  <div align="left">
      <img src="/VPC1.JPG"></img>
 </div>