# SampleProjectApplication

Mvp design pattern:

1: Fetched the list of users from service.
2: Saved the fetched list in local sqlite database for later retrievel
3: Added Search functionality according to username

Explanation:
1:Created UI design. Used butter knife library to access ui components directly from xml file to reduce the usage of findViewById
2-For fetching the data from web service used retrofit library. Created model pojo class accroding to json response.
This model class is passed as return call function inside GET request in interface defined with endpoints of url.
Retrofit client is added as a singleton object for restriction of multiple creation of same retrofit object.
3- MVP: It contains model view and presenter. Presenter is acting as an middleware between model and view. 
View (UI) - Calling of presenter class methods which will intetact with interactor to get the data and pass it to the view components
Interface callbacks are added inside view that gets called when data is fetched from service or local database. 
Interactor - Responsible for fetching of data from webservice or from local database and accordingly pass the results to presenter through callbacks implemented inside presenter.
Whereas presenter is holding the reference to view interface for creating callbacks and providing data to View components through interface callbacks 
that are implemented with View(Activity)
4- Logic of search: Created copy of current arraylist so that operations can be performed on current arraylist.
Based on user entered keywords, code will check weather entered keywords are contained in any of the names that are present in list.
if yes then that particular item is added to current arraylist. This check is performed using advanced for loop. 
