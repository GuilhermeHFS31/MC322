import java.util.ArrayList;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;
    
    public Frota(String code, ArrayList<Veiculo> listaVeiculos) {
        this.code = code;
        this.listaVeiculos = listaVeiculos;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean verificaVeiculo(String placa){
        for (int i = 0; i < listaVeiculos.size(); i++){
            if(listaVeiculos.get(i).getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    public Veiculo buscaVeiculo(String placa){
        int i;
        for(i = 0; !listaVeiculos.get(i).getPlaca().equals(placa); i++){
        }
        return listaVeiculos.get(i);
    }


    public void imprimeFrota(){
        for (int i = 0; i < listaVeiculos.size();i++){
            System.out.println(listaVeiculos.get(i).toString());
        }
    }

}
