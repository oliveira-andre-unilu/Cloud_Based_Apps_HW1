# Cloud-based application: Assignment 1

## Part 3: Deploying the application within a local Kubernetes cluster  

### Introduction

For this part of this assignment I've decided to create choose the **kind** since it will run on docker itself thus ensuring easy of deployment.    
Additionally, I've decided two different replicas pf my service in my kind cluster.

### Main Architecture of the cluster

I've decided to deploy the kind cluster with the help of three `.yaml` files, these files are:

- `kind-cluster.yaml`: This defines the kind cluster that will be running inside docker
- `deployment.yaml`: This document will deploy the main Kubernetes Deployment
- `service.yaml`: This document will define the main application service

### How to run the cluster

**Prerequisites:**

You will need to make sure that you have `docker`, `kubectl` and `kind` installed in your machine.

**Running the cluster:**

In order to run this cluster in your machine, you will need to start by creating a new cluster kind in your machine, you can do so by executing the following command:

```bash
kind create cluster --name cloud-based-hw1-cluster --config kind-cluster.yaml
```

Once the cluster is up and running you will need to apply the defined manifests (deployment and service) in the kind cluster, this can be done by running the following commands:

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

The cluster should be now up and running.

**Testing the implementation**

You can easily test if the cluster is running correctly by running the following commands:

```bash
kubectl get pods
kubectl get deployments
kubectl get services
```

You should get something like this:

```
kubectl get pods 
NAME                               READY   STATUS    RESTARTS   AGE
cloud-based-hw1-7cd6688bc6-hqdxf   1/1     Running   0          64s
cloud-based-hw1-7cd6688bc6-s6j65   1/1     Running   0          64s

kubectl get deployments 
NAME              READY   UP-TO-DATE   AVAILABLE   AGE
cloud-based-hw1   2/2     2            2           76s

kubectl get services 
NAME                      TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
cloud-based-hw1-service   NodePort    10.96.82.223   <none>        8080:30080/TCP   38s
kubernetes                ClusterIP   10.96.0.1      <none>        443/TCP          7m27s
```

**Accessing the web service**

Additionally, you should be able to access the web-service via the port `30080`, here is an example of an web-service call:

`http://localhost:30080/api/getPopulation?year=1995`