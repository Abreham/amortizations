This is a high jump java code re-factor exercise for Paypal code interview
=============================================================================

Assumptions: 
=============
	 since we are dealing with financial data, we need to have a data-structure 
	 with better accuracy in calculation can have larger bit than the normal 64bit double.
	 - use BigDecimal
     
     we need to be able to use/demo object oriented design
       - Object/classes: Loan,Money,Schedule,constants/Enums.
       - separation of concerns, avoid object creation detail either in the client/or test,
         use a factory which will deal with that detail      
       - use utility class for validation, as they are not part of the bean.
       - have custom exception class, Instead of throwing direct java exceptions like IllegalArrgumentExcepion.
       - presentation is a separate concern of it's own, move it to a different class MyConsole.
       - Move the test client to AmortizationScheduleTest.java.      
       - Make code readable and self documented.
       
Further Improvements
=======================
        - add Unit tests.
        - add UI using web or some other form.
========================================================================================
       
 
