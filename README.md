# CovidHelp

### HOMEPAGE

The homepage will contain a brief description of the website, two buttons that redirect to the register and login pages, and some statistics about what users accomplished (e.g. X requests fulfilled, Y volunteers registered, Z shopping bags delivered, Y pets walked, etc.), collaborators.

### REGISTER

The register page will have the following fields: Email, password, first and last name, birthdate, county, city, street, phone number, profile picture (optional)). The user should choose his account type(role): Volunteer or Requester. After he finishes registering, he will be redirected to the following pages based on the account type he chose: Requester > OWN REQUESTS, Volunteer > BROWSE REQUESTS

### LOGIN

This page allows users to login using their email and password. The page redirects to pages in a similar way to the register page, based on the user’s role.

### OWN REQUESTS

This page should contain all the requests the user has placed.
* If the user has no requests placed, prompt him to place one by pressing a button to “Place a request”, then redirect to PLACE REQUESTS page.
* If the user has at least one request placed, show them in a list view along with the relevant information (request type, details, address, start and end date, status (Pending admin approval/Approved/Rejected/Completed)). At the bottom of the list view there should be a “Cancel request” button to allow the user to cancel that request.
     * If the status is Rejected, add a field that describes the reason
     * If the status is Approved, show a collapsible list that specifies how many volunteers applied to that request. When the list is un-collapsed, show all the users who applied to that request (profile picture, name, nr. of requests completed, and two buttons to get contact info and to mark that user as having completed the request)
     
### PLACE REQUESTS

This is the page where Requesters can place a new request.
The page should contain the following fields: A dropdown for the request type (shopping, pet walking, taking out the trash, etc.), a text field for a short description of the request and the necessary details (shopping list, budget, shop, etc.), starting and ending date for the request. After placing the request, notify the user that it was placed successfully and redirect to the OWN REQUESTS page.

### BROWSE REQUESTS

This is the page where volunteers are able to browse, filter, and apply to requests that have been Approved and not yet Completed.
The Volunteers should be able to filter the requests based on Type (one or more) and/or Location (City). Each request that fulfills the user’s criteria will be shown in a list view, with each item containing: Request type, city, street, end date, details, and a button to apply to that request.

### ADMIN PAGE

This is the page where admins can Approve or Reject requests.
The page contains a list view of all the requests that are Pending approval. Each list item contains the following request information: type, address, user name, details, start and end date, and two buttons to either Approve or Reject the request. If the request is rejected, the reason must be specified in a text field that pops up once the Reject button is pressed.

### PROFILE PAGE

Once the user is logged in, there should be a button on each page that redirect to the user’s profile. On this page, users can see and/or modify their own information (Profile picture, first and last name, email, password, role*, birthdate*, address, nr. of completed requests*(if user is Volunteer), phone nr. (* - unmodifiable).

---
### Entity Relationship Diagram

![ERD](https://i.ibb.co/qd6T23y/Covid-Help-1.png)
