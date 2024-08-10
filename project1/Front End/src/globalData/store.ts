import { UserInterface } from "../interfaces/UserInterface";


export const store:any = {
    //we typically want to store user session info on the front end
    //... for the sake of personalization, role-based behavior, and easier HTTP requests
    loggedInUser: {
        userId: 0,
        username: "",
        role: ""
    } as UserInterface,
    //NOTE: we could have made a UserInterface (and might still)

    //Think about your requirements when it comes to storing global data
    //you only NEED to globally store data you intend to use in multiple contexts

    //for instance, we could have stored the base URL to our backend
    baseURL: "http://localhost:8080/"

    //In p2, we'll store our JWT tokens here to be included in requests
    //We could have even sroted the User's Car List after the first get all to avoid repeat calls to the DB
    
    //(Check if it exists in the store first, before sending HTTP requests)

}