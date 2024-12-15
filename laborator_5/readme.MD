# Implement the following exercises

## Objectives

Main objectives of this lab are:
- Understand how to analyze system requirements and represent them using various types of UML diagrams.
- Learn to distinguish between different UML diagram types and their appropriate use cases.
- Apply UML modeling techniques to represent the structure and behavior of a software system.

In this lab, you will create multiple UML diagrams for a hypothetical **Online Event Ticketing System**. Consider that this system allows customers to browse events, purchase tickets, receive electronic tickets with QR codes, and check in at the event venue. You will use the following UML diagrams to model different aspects of this system:

- Use Case Diagram
- Class Diagram
- Sequence Diagram
- State Machine Diagram
- Activity Diagram

Before starting:
- Identify the primary actors and key functionalities of the system.
- Consider the lifecycle of a ticket: from being available for purchase, purchased, used at check-in, and potentially revoked or expired.
- Think about the workflows and processes involved (e.g., purchasing a ticket, validating a ticket, checking in a customer).

Tools:
- Consider using [StarUML](http://staruml.io/) tool for drawing diagrams required in this lab. This is a free tool which can be installed and run on any of the major operating systems. 

## Lab instructions

For all the exercises below:
1. Start by identifying the necessary elements (actors, classes, states, activities, etc.) based on the Online Event Ticketing System scenario.
2. Draw the requested UML diagrams according to standard UML notation.
3. Document any assumptions made that are not explicitly stated in the problem description.

You do not have to implement any code in this lab. The focus is on creating and understanding UML diagrams.

## Exercise 1: Use Case Diagram

**Goal:** Identify and visualize the main user interactions with the Online Event Ticketing System.

**Instructions:**
1. Identify the primary actors (e.g., Customer, Event Organizer, System Administrator).
2. Determine the main use cases (e.g., Browse Events, Purchase Ticket, Validate Ticket, Check-in at Event).
3. Create a Use Case Diagram that shows the relationships between actors and use cases.
4. Include any use case generalizations or extensions if necessary.

**Expected result:** A Use Case Diagram providing a high-level overview of the system’s functionalities from the user’s perspective.

## Exercise 2: Class Diagram

**Goal:** Model the structural components and relationships within the system.

**Instructions:**
1. Identify the main classes (e.g., Event, Ticket, User, Payment, QRCodeGenerator).
2. Determine the attributes and operations for each class.
3. Specify relationships (associations, aggregations, compositions, inheritances) between classes.
4. Create a Class Diagram that reflects the system’s static structure.

**Expected result:** A Class Diagram showing the key classes, their attributes, methods, and the relationships that define the system’s domain model.

## Exercise 3: Sequence Diagram

**Goal:** Show the interaction and message flow between objects to realize a specific use case.

**Instructions:**
1. Select a use case from the Use Case Diagram (e.g., “Purchase Ticket”).
2. Identify the participating objects (e.g., Customer, Event, Ticket, PaymentService, QRCodeGenerator).
3. Model the sequence of interactions, including synchronous and asynchronous calls if needed.
4. Draw a Sequence Diagram that represents the step-by-step flow of the scenario.

**Expected result:** A Sequence Diagram illustrating how various objects interact to complete the chosen use case.

## Exercise 4: State Machine Diagram

**Goal:** Model the internal lifecycle and states of a critical entity in the system.

**Instructions:**
1. Consider a Ticket entity which moves through states such as Available, Reserved, Purchased, CheckedIn, and possibly Invalid.
2. Identify transitions that cause the state changes (e.g., purchase action, scan at venue).
3. Create a State Machine Diagram detailing all states of a Ticket and the events that trigger transitions.

**Expected result:** A State Machine Diagram showing how a Ticket’s state changes over time in response to internal and external events.

## Exercise 5: Activity Diagram

**Goal:** Represent the workflow of a complex process within the system.

**Instructions:**
1. Consider the end-to-end process of handling ticket purchasing and event check-in.
2. Identify activities, decisions, forks, merges, and start/end points.
3. Draw an Activity Diagram illustrating the flow of actions, including purchasing a ticket, generating a QR code, sending it to the customer, and checking in at the event.

**Expected result:** An Activity Diagram showing the flow of actions and decisions for a major operational process in the system.

---

**Submission and Review:**
- Provide all UML diagrams as image files or PDF exports.
- Ensure that the diagrams use standard UML notation.
- Review the diagrams for consistency, clarity, and completeness.
- Submit your diagrams along with a brief explanation of key design decisions and assumptions made.

**Note:** The focus of this lab is on conceptual modeling with UML diagrams. You are not required to write code, but you should think about how these diagrams would guide a future implementation.
