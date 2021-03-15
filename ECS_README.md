# ECS (Elastic Container Service)
## Basic questions

- [X] What is ECS?
 - It is a control plane which manages creation, monitoring & scaling of containers.
 - It has a group of components, namely tasks, services, clusters responsible for running multiple containers of desired capacity reliably.
    
- [X] How do you define a container in ECS?
 - Task definition in ECS is where you define your container. Basically, you define all your container specifications like image name, container port, env variables, volumes, etc., Docker images used in the task definition can be downloaded from either docker hub or AWS ECR(Elastic container registry).
    
- [X] How do you run your container?
 - You attach an ECS service to your task definition and mention how many instances of your container to run. It is the service responsibility to maintain the desired no. of instances 24X7. You can use the same service to run multiple task definitions(docker containers).

- [X] Where do you host your containers?
- **ECS cluster is where you run your containers. It is a group of EC2 machines which are dedicated to run docker containers. It takes the task definitions(container creation requests) and distributes them equally b/w the nodes in the cluster.**
- They can be located in multiple Availability zones of a region for distributing your containers across multiple zones for higher availability and fault tolerance.
  
- [X] What are different types of clusters? 
- Fargate - It abstracts the whole cluster infrastructure and manages on your behalf. So, you are left with the responsibility of when to launch your containers.    
- EC2 - You will be creating the cluster with the EC2 instances of your choice. You will have to be responsible for the cluster management.
    
- [X] How to scale your cluster when the no. of containers grow?    
- If you use Fargate, it does most of the heavy lifting. You don't have to bother about your cluster infrastructure. For EC2 type cluster, you have to define cloud watch rules to auto scale to your instances.
- Suppose when an EC2 instances in your cluster is running out of resources due to over CPU consumption, over memory consumption. ECS can add a new EC2 instance to the cluster to take up the new load. We can also define custom rules for auto scaling. An example of such custom rule is no. of instances to be run on a single instance in the cluster.  
