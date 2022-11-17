
package com.biblioteca.biblioteca.modelos;

import java.util.Calendar;
import java.util.Date;

        
public class Leitor extends Pessoa{
    private String numCadastro;
    
    public Leitor(){}
    
     public Leitor(String pNome, String pTelefone, String pDocumento, Calendar pDataNascimento, Endereco pEndereco, String pNumCadastro) {
      super(pNome, pTelefone, pDocumento, pDataNascimento, pEndereco);
      this.numCadastro = pNumCadastro;
    }   


    public String getNumCadastro() {
        return numCadastro;
    }

    public void setNumCadastro(String numCadastro) {
        this.numCadastro = numCadastro;
    }
     
     
}
