# Drinks-Ahoy

The following project contain a beer information API that uses Kotlin, and Android studio as the platform of choice.

The goal of the project is to create a functioning application. The targeted SDK for this application is Android 31.

The following application will highlight key technologies such as:
      User authentication
      Accessing an API
      Parsing an API response
      Persisting API data
      Displaying data in the UI
      
 The above-mentioned tasks will be accomplished using well designed, structured and documented code. 
 
 Goal at start of project:
 
 Initially a quick information of the drink will be displayed that includes:
       Name 
       Strength
       Tagline
       Above 5% abv show a strong icon
       No truncation
       
 Upon clicking the quick information item expands into detailed view with the following fields displayed:
       Name
       Strength (abv)
       Description
       Food pairing notes (zero to many)
       Navigation options
       
Bonus feature:
      Functionality to download information on favourite drinks to view locally.
      Making sure downloaded information is persistent.
       
Reviewer Note (22/08/2022):
      As of the date mentioned the app is able to display beer information that conforms to both user study 1 and 2
      The app has a back-end structure for storing downloaded beer data using SQLite to store beer information, however true the functionality is yet to be enabled for       user via a button click at the front end.
      Finally, a recycler view is to be created for which the database read functionality can be used to inflate the list.     
