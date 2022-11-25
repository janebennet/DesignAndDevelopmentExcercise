
package DD;

import java.util.ArrayList;

public class PrintHouseholds {
    public static void display(String[][] data){
        ArrayList<Household> households = new ArrayList<>();
        //for each line of data, create a Person  
        for (String[] line : data) {
            //using RegEx to remove non-alphanumeric characters and trailing space
            String street = line[2].replaceAll("[^a-zA-Z0-9 ]| $", "");

            Person p = new Person(
                    line[0],
                    line[1],
                    street + " " + line[3] + " " + line[4].toUpperCase(),
                    Integer.parseInt(line[5]));
            
            //placing a person in a household based on address
            for (Household h : households) {
                if (h.address.equals(p.address.toUpperCase())) {
                    h.addPerson(p);
                    p.inHouse = true;
                    break;
                }
            }
            //if the Person is not in the existing household, create new household
            if (!p.inHouse) {
                households.add(new Household(p.address.toUpperCase(), p));
            }    
        }
        
        //iterate through households and print out data 
        for (Household h : households) {
            System.out.print("Household ");
            System.out.print(h.address + ", " + h.people.size() + " person(s). ");
            
            //sort the people in the household 
            ArrayList<Person> sortedP = h.sortPeople(h.people);
            for (Person p : sortedP) {
                //according to the requirement I do not display people younger than 18
                if (p.age > 18) {
                    System.out.print(p.firstName + ", " + p.lastName + ", "
                            + p.address + ", " + p.age + ". ");
                }
            }
            System.out.println();
        }
    }
     public static void main(String[] args) {
        String[][] data = {{"Dave", "Smith", "123 main st.", "seattle", "wa", "43"},
        {"Alice", "Smith", "123 Main St.", "Seattle", "WA", "45"},
        {"Bob", "Williams", "234 2nd Ave.", "Tacoma", "WA", "26"},
        {"Carol", "Johnson", "234 2nd Ave", "Seattle", "WA", "67"},
        {"Eve", "Smith", "234 2nd Ave.", "Tacoma", "WA", "25"},
        {"Frank", "Jones", "234 2nd Ave.", "Tacoma", "FL", "23"},
        {"George", "Brown", "345 3rd Blvd., Apt. 200", "Seattle", "WA", "18"},
        {"Helen", "Brown", "345 3rd Blvd. Apt. 200", "Seattle", "WA", "18"},
        {"Ian", "Smith", "123 main st ", "Seattle", "Wa", "18"},
        {"Jane", "Smith", "123 Main St.", "Seattle", "WA", "13"}};
        
        display(data);
    }   
}

//A household is defined by the U.S. Census Bureau as all the people who occupy a single housing unit, 
//regardless of their relationship to one another.

//Current solution relies on the specific order and type of the data in the input. 
//Input is a list of lists of strings, where the iformation is always provided in the same order.
//If the input is provided in the specified format, solution can handle any number of "people" records.
//The solution does not verify the data type or if the input is empty.
//The solution can handle extra items in the "person details" list. It will ignore any Strings after the age.
//It will work if some fields are empty, but not the age. Age can only be an Integer.


//Test Cases:
//Happy path (valid input) - Pass, the solution prints out the households and people data according to specification.
//Empty input array - Pass, solution does not do anything.
//Empty values - Pass, except for the age. Fails if age is empty or not Integer. 
//Negative value passed as age ("-56") - Pass. No error message.
//Duplicate persons - Pass, not filtered, are added to the household
//Extra values in the person array - Pass, ignored.
//Less items in the person array (less than 6) - Fail.
//Different data types (char, int etc, instead of String) - Fail.
//Wrong order of the parameters (for ex, address, name, age, last name) - Fail.
//Different size of the data (extra persons, less people) - Pass.
//Abbreviations vs full name in the address - Fail, (Ave != Avenue, St != Street).
//Different capitalization in address (STREET, SeATTle) - Pass.
//Other languages (for ex, cyrillic "Джейн", "Сміт") - Pass for Name, Last name, fail for address because of the RegEx.
//Special characters in name or address - Pass, name is displayed with special characters, address will ignore the special characters.

