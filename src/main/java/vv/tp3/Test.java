package vv.tp3;

public class Test {

    public static void main(final String[] args) {

    }

//    private static void testBoucleImbriquees(boolean a, boolean b, boolean c) {
//        while(a){
//            while(b){
//                while(true){
//                    break;
//                }
//            }
//        }
//    }

    private static int testWhile() {
        while(true){
            break;
            while(true){
                while(true){
                    return false;
                }
            }
        }
        return 0;

    }
}
