package TestCases;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

interface Test_API {
    int user_Limit = 10;

    void print_Product(String name, int UID);

    void add_User(String name);

    boolean give_Access(String name, int UID);

    void get_Pid(int UID);

    default void error_Statement(String url) {
        System.out.println("Sorry You are not valid candidate to use API \n Please Register here : " + url);
    }
}


class Test_Api_Applier implements Test_API {
    private final HashMap<Integer, Map<String, List<String>>> candidate_profiles;
    private final List<Boolean> access;
    private final ReentrantLock l;
    private final Object obj;
    private int user;

    protected Test_Api_Applier() {
        l = new ReentrantLock(true);
        candidate_profiles = new HashMap<>();
        access = new ArrayList<>();
        this.obj = new Object();
    }

    private static class Private_Exception extends Exception {
        Private_Exception() {
            super("Error in printing User List");
        }
    }

    @Override
    public void print_Product(String name, int UID) {
        synchronized (obj) {
            if (give_Access(name, UID)) {
                for (Map.Entry<Integer, Map<String, List<String>>> entry : candidate_profiles.entrySet()) {
                    for (Map.Entry<String, List<String>> entry1 : entry.getValue().entrySet()) {
                        if (entry.getKey() == UID) {
                            System.out.println("User Name : " + entry1.getKey() + " - " + entry1.getValue());
                        }
                    }
                }
            } else {
                error_Statement("https://www.google.com");
            }
        }
    }

    public void add_User(String name) {
        if (user <= user_Limit) {
            access.set(user, true);
            l.lock();
            HashMap<String, List<String>> userMap = new HashMap<>();
            userMap.put("name", add_Product(name));
            l.unlock();
            synchronized (obj) {
                candidate_profiles.put(user, candidate_profiles.getOrDefault(user, userMap));
                notifyAll();
            }
            user++;
        }
    }

    private @NotNull List<String> add_Product(String name) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(name + " Product " + i);
        }
        return list;
    }

    @Override
    public boolean give_Access(String name, int UID) {
        if (candidate_profiles.containsKey(UID)) {
            return candidate_profiles.get(UID).get(name).contains(name);
        } else return false;
    }

    @Override
    public void get_Pid(int UID) {
        if (candidate_profiles.containsKey(UID)) {
            return_Pid(UID);
        }
    }

    public void return_Pid(int UID) {
        if (access.get(UID)) {
            System.out.println("User ID : " + UID + " Candidate is Valid can Access Services");
        } else {
            System.out.println("User ID : " + UID + " Candidate is not Valid can't Access Services");
        }
    }

    public void increment_User() {
        user += 1;
    }

    private void user_List() {
        synchronized (obj) {
            for (Map.Entry<Integer, Map<String, List<String>>> entry : candidate_profiles.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            notifyAll();
        }
    }
}


public class Conflicting_API {

}
