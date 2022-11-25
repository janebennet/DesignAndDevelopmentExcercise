
package DD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Household {

    String address;
    ArrayList<Person> people = new ArrayList<>();

    public Household(String addr, Person p) {
        address = addr;
        people.add(p);
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public ArrayList<Person> sortPeople(ArrayList<Person> ppl) {
        //custom sorting. Sort by last name, then by first name
        Collections.sort(ppl, new Comparator() {
           
            public int compare(Object o1, Object o2) {
                int res = ((Person) o1).lastName.compareTo(((Person) o2).lastName);
                if (res == 0) { //LastName is the same
                    return ((Person) o1).firstName.compareTo(((Person) o2).firstName);
                } else {
                    return res;
                }
            }
        });
        return ppl;
    }
}
