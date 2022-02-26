public class Test {


    public static String solution(String[] participant, String[] completion) {
        int iLengthP = participant.length;
        int iLengthC = completion.length;

        String answer = "";

        for(int i = 0; i < iLengthP; i++){

            for(int j = 0; j < iLengthC; j++){
                System.out.println(participant[i]);
                System.out.println(completion[i]);
                if(participant[i].equals(completion[j])){
                    continue;
                }else {
                    answer = participant[i];
                    return answer;
                }
            }


        }


        return answer;
    }

    public static void main(String[] args) {
//        String[] participant = {"leo", "kiki", "eden"};
        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//        String[] participant = {"leo", "kiki", "eden"};
//        String[] completion = {"eden", "kiki"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};
//        String[] completion = {"eden", "kiki"};
        String sol = solution(participant, completion);
        System.out.println("sol = " + sol);
    }
}
