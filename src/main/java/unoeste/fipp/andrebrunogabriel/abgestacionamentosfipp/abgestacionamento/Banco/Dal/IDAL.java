
package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import java.util.List;

public interface IDAL <T>{
    public boolean inserir(T entidade);
    public boolean alterar(T entidade);
    public boolean deletar(int id);
    public boolean dependentes(int id);

    public T Select(int id);
    public List<T> Select();
    public List<T> Select(String filtro);
}
