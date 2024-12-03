# IMDBRipOff Documentation

## Table of Contents
<!-- TOC -->
* [IMDBRipOff Documentation](#imdbripoff-documentation)
  * [Table of Contents](#table-of-contents)
  * [Authors](#authors)
  * [Summary](#summary)
  * [Installation Instructions](#installation-instructions)
  * [Software Features](#software-features)
  * [Section for Feedback](#section-for-feedback)
  * [Contribution Guidelines](#contribution-guidelines)
<!-- TOC -->
## Authors
Yanzhen Chen - YheChen,
Shawn Kapcan - skap146,
Rowan Houston - RowanHouston,
Grant Bogner - Mighto360

## Summary
This project was made to provide a simple application for reviewing movies and searching for the reviews of others. 
On the main page, a user can browse reviews and sort/filter them as they see fit (e.g. they can filter by highest review
score). Each review is associated with a movie, contains a score from 1-5 (whole number only for simplicity), and
has a text section where the user can explain their reasoning behind their review score. The user can write their
own review on any movie that exists within our servers. While this project was meant to provide a space where
users can create and share movie reviews, it can be expanded in the future to accomondate reviews for other major
media forms such as books, TV shows, or video games.

## Installation Instructions
We assume you have a Java IDE set up (such as IntelliJ)

This code was created and tested in Java Version 22. It should run in later Java versions as well, however we cannot 
guarantee this program will run in older Java versions. Let us know if there is a version of Java newer than ver 22
where this program does not behave as expected.


To install this program, clone the repository https://github.com/YheChen/IMDBRipOff/tree/main to IntelliJ using
the "Get from VCS" feature. To run this program, just run main from IntelliJ. This program also depends on the
following external libraries (not provided by Java) to function:

- com.mongodb.client 
- org.bson
- okhttp3
- org.json 
IMPORTANT: UNLESS OTHERWISE SPECIFIED, we use the latest version of these libraries.

Ensure your computer has the ability to enable pop-ups, as a JSwing pop up will be displayed when this program runs.

## Software Features

For this section, click on the following linl:
https://docs.google.com/document/d/1WrZn4NF4zF2Q5t1t3NlUp7TlRyME9yvZMYFocvi2jBc/edit?tab=t.0

## Section for Feedback

We would love to hear your feedback! To provide feedback, either contact shawn.kapcan@mail.utoronto.ca or post the issue
directly on GitHub. If the amount of feedback becomes too overwhelming through email and git alone, we will open
a google forms so users of our program can provide feedback, and this manual will be updated accordingly.

Rules for Providing Feedback
- No personal insults towards authors/contributors of this program, if one fails to follow this rule, then the feedback
will be disregarded
- If there is a bug, provide a brief and consice description of said bug and how it triggers
- If you want a new feature to be implemented, write in 1-2 sentences the feature you are interested in
- Expect a response time of 3-5 business days for feedback
- Depending on what the feedback is, the time to implement said feedback can vary from same-day to a few months

## Contribution Guidelines

We encourage and appreciate contributions to this project to improve it! To make a contribution, first go to 
https://github.com/YheChen/IMDBRipOff/tree/main and fork this repository. To fork, click fork in GitHub. Select 
copy the main branch only, as you can create new branches later. Next, clone the forked repo in your preferred IDE
of choice. You can now create branches as you see fit to make changes to this project. Once you are happy with your
changes, create a merge request, following the below guidelines.

Merge Request Guidelines:

- Create a pull request for the original project
- Describe why you think merging your changes to the original repo is important. 
(e.x. describe the new feature added, bug fix, etc.)
- Next, our team will review the changes. We will test the changes and only once a majority of us agree that
a merge should be performed will it be performed.
- In the event of a conflict, we can choose to accept the pull request, but we may only take some of your changes
to resolve such conflict.
- Add your email address to the pull request so we can contact you regarding your pull request

We will notify you by email regarding the results of your pull request. If it is denied, we will give a brief
explanation regarding why it was denied and if it is accepted we will thank you for your contribution!

## Licensing

Go to LICENSE.txt for details about licensing of this project.





