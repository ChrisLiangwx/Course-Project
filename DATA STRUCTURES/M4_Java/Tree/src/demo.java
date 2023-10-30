public class demo {
    public static void main(String[] args) {
        String line= "D8400342LaPorte                  0045JA  1";
        if(line.charAt(0) == 'I'){
            String name = line.substring(8, 32);
            name = name.replaceAll("\\s", "");
           System.out.println(name);
        }else{
//            String name = line.substring(8, 32).trim();
            String name = line.substring(8, 32);
            name = name.replaceAll("\\s", "");
            System.out.println(name);
        }


    }
}
