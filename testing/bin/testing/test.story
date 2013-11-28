Scenario: setTitle

Given a book x with a title Hello an author world a release date 2011 and a price of 10.00 
When the title is set to nothing
Then the book is still valid