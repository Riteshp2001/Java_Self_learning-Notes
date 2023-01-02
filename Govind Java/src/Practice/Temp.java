package Practice;

class Temp {
    static char Acii(int key, char character) {
        char ans = 'x';
        if (character >= 'a' && character <= 'z') {
            if (character + key >= 'z') {
                ans = ((char) ((((int) character) + key) % 122 + 'a' - 1));
            } else {
                ans = (char) (character + key);
            }
        } else if (character >= 'A' && character <= 'Z') {
            if (character + key >= 'Z') {
                ans = ((char) ((((int) character) + key) % 90 + 'A' - 1));
            } else {
                ans = (char) (character + key);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Acii(2, 'Z'));
    }
}
