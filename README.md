# README

This repository contains 3 child projects:

  - javascript
  - aws-java
  - quarkus-java

Each project contains an AWS Lambda implementation using the nominated technology. These are the projects that were used to get timings for the different lambdas for the blog post [Improving the cold start times of Java AWS Lambdas using GraalVM and native images](https://shinesolutions.com/?p=25678).

All projects implement a simple Greeting Lambda that accepts a name and a greeting in the body, and returns a string with the following format "<greeting> <name>", unless the name is "Stuart".

This initial Lambda is trivial but provides enough indicators of speed improvements with GraalVM.
