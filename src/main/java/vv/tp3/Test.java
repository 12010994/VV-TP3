package vv.tp3;

public class Test {

    public static void main(final String[] args) {
        testBoucleImbriquees();
        testWhile(true);
        testWhile(false);
    }

    private static void testBoucleImbriquees() {
        while(true){
            while(true){
                while(true){
                    break;
                }
                break;
            }
            break;
        }
    }

    private static void testWhile(boolean b) {
        while(b){
            break;
        }
    }
}
