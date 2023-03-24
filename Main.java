public class Main {
    
    public static void main(String[] args){
        Cliente C = new Cliente("Kaire", "447.652.018-99", "15/08/2003", null, 0);
        System.out.println(C.getNome());
        System.out.println(C.getCpf());
        if(C.validarCPF(C.getCpf()) == true){
            System.out.println("REAL");
        } else{
            System.out.println("AAAAAAAAAAA");
        }
        
        System.out.println(C.toString());
        
    }
}
