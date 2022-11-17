/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.rdn;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.biblioteca.biblioteca.modelos.Endereco;
import com.biblioteca.biblioteca.modelos.Leitor;



public class LeitorRdn {
     public int inserir(Leitor leitor) throws SQLException {
        
        StringBuilder sql = new StringBuilder();
        int linhasAfetadas = 0;
        
        
        sql.append("    INSERT INTO leitor                                   ");
        sql.append("    (                                                    ");
        sql.append("       nome_leitor                                       ");
        sql.append("        ,telefone                                        ");
        sql.append("        ,email                                           ");
        sql.append("        ,documento                                       ");       
        sql.append("        ,dataNascimento                                  ");       
        sql.append("        ,numero_cadastro                                 ");
        sql.append("        ,logradouro                                      ");
        sql.append("        ,bairro                                          ");
        sql.append("        ,cep                                             ");
        sql.append("        ,cidade                                          ");
        sql.append("        ,complemento                                     ");
        sql.append("        ,numero                                          ");
        sql.append("        ,uf                                              ");
        sql.append("     )                                                   ");
        sql.append("     VALUES                                              ");
        sql.append("     (                                                   ");
        sql.append("        ?                                                ");        
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("        ,?                                               ");
        sql.append("    );                                                   ");
     
        
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        
        stmt.setString(1, leitor.getNome());
        stmt.setString(2, leitor.getTelefone());
        stmt.setString(3, leitor.getEmail());
        stmt.setString(4, leitor.getDocumento());      
        stmt.setDate(5, new java.sql.Date(leitor.getDataNascimento().getTimeInMillis()));
        stmt.setString(6, leitor.getNumCadastro());
        stmt.setString(7, leitor.getEndereco().getLogradouro());
        stmt.setString(8, leitor.getEndereco().getBairro());
        stmt.setString(9, leitor.getEndereco().getCep());
        stmt.setString(10, leitor.getEndereco().getCidade());
        stmt.setString(11, leitor.getEndereco().getComplemento());
        stmt.setString(12, leitor.getEndereco().getNumero());
        stmt.setString(13, leitor.getEndereco().getUf());
         
        
        linhasAfetadas = stmt.executeUpdate();
        
        System.out.print("linhas afetadas: " + linhasAfetadas);

        stmt.close();
        conn.close();
         
        return linhasAfetadas;   
    }
    
        public ArrayList<Leitor> obterTodos()  {
        
            ArrayList<Leitor> retorno = new ArrayList<Leitor>();
            
             try {
                 
               StringBuilder str = new StringBuilder();
               
                str.append("select          a.id_leitor                      ");              
                str.append("                ,a.nome_leitor                   ");
                str.append("                ,a.telefone                      ");
                str.append("                ,a.email                         ");
                str.append("                ,a.documento                     ");                      
                str.append("                ,a.dataNascimento                ");             
                str.append("                ,a.numero_cadastro              ");
                str.append("                ,a.logradouro                    ");
                str.append("                ,a.bairro                        ");
                str.append("                ,a.cidade                       ");
                str.append("                ,a.cep                           ");
                str.append("                ,a.complemento                   ");
                str.append("                ,a.numero                        ");
                str.append("                ,a.uf                            ");
                str.append(" from leitor a                                   ");
                
                
                     
       
            Connection conn = new ConnectionFactory().getConnection();

    
            Statement stmt = conn.createStatement();

         
            ResultSet rs = stmt.executeQuery(str.toString());
            
            
            while (rs.next()) {
                
                Leitor leitor = new Leitor();
                
                leitor.setId(rs.getInt("id_leitor"));
                leitor.setNome(rs.getString("nome_leitor"));
                leitor.setTelefone(rs.getString("telefone"));
                leitor.setEmail(rs.getString("email"));
                leitor.setDocumento(rs.getString("documento"));                
               
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("dataNascimento"));
                leitor.setDataNascimento(calendar);                
                leitor.setNumCadastro(rs.getString("numero_cadastro"));
                              
                Endereco end = new Endereco();
                
                end.setLogradouro(rs.getString("logradouro"));
                end.setNumero(rs.getString("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setUf(rs.getString("uf"));
                end.setComplemento(rs.getString("complemento"));
                
               
                leitor.setEndereco(end);

                retorno.add(leitor);
                
             }
        
            rs.close();
            stmt.close();
            conn.close();

            } catch (SQLException e) {
            System.out.println(e);
            }   

        return retorno;    
    }
        
        public int excluir(int id) {
            
            int numeroLinhasAfetadas = 0;

        try {
            
            String str = "delete from leitor where id_leitor = ?";
            
           
            Connection conn = new ConnectionFactory().getConnection();

           
            PreparedStatement statement = conn.prepareStatement(str);
            statement.setInt(1, id);

            
            numeroLinhasAfetadas = statement.executeUpdate();
            
            
            conn.close();
            statement.close();
            
            } catch (SQLException e) {
            System.out.println(e);
        }
        return numeroLinhasAfetadas;
     }

     public int deletar(int id) {

        int numeroLinhasAfetadas = 0;

        try {

            String str = "delete from leitor where id_leitor = ?";

            //RECUPERAR A CONEXÃO 
            Connection conn = new ConnectionFactory().getConnection();
            //INSTANCIAR O COMANDO
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            //CRIAÇÃO DE PARAMETROS
            stmt.setInt(1, id);          
            
            //EXECUTA O DELETE
            numeroLinhasAfetadas = stmt.executeUpdate();

            //FECHA A CONEXÃO E O STATEMENT
            conn.close();

            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return numeroLinhasAfetadas;

    }
     
        public int alterar (Leitor leitor)  {

        StringBuilder str = new StringBuilder();
        int numeroLinhasAfetadas = 0;
        
        try {
            
                str.append(" update leitor set  nome_leitor  = ?              ");
                str.append("                   ,telefone    = ?              ");
                str.append("                   ,email        = ?             ");
                str.append("                   ,documento      = ?           ");                      
                str.append("                   ,dataNascimento   = ?         ");
                
                str.append("                   ,numero_cadastro    = ?       ");
                str.append("                   ,logradouro     = ?           ");
                str.append("                   ,bairro         = ?           ");
                str.append("                   ,cidade         = ?          ");
                str.append("                   ,cep       = ?                ");
                str.append("                   ,complemento     = ?          ");
                str.append("                   ,numero           = ?         ");
                str.append("                   ,uf         = ?               ");
                str.append(" where id_leitor = ?                             ");
                
        
        Connection conn = new ConnectionFactory().getConnection();
        
        PreparedStatement stmt = conn.prepareStatement(str.toString());
               
         stmt.setString(1, leitor.getNome());
        stmt.setString(2, leitor.getTelefone());
        stmt.setString(3, leitor.getEmail());
        stmt.setString(4, leitor.getDocumento());
                
        stmt.setDate(5, new java.sql.Date(leitor.getDataNascimento().getTimeInMillis()));
        stmt.setString(6, leitor.getNumCadastro());
        stmt.setString(7, leitor.getEndereco().getLogradouro());
        stmt.setString(8, leitor.getEndereco().getBairro());
        stmt.setString(9, leitor.getEndereco().getCep());
        stmt.setString(10, leitor.getEndereco().getCidade());
        stmt.setString(11, leitor.getEndereco().getComplemento());
        stmt.setString(12, leitor.getEndereco().getNumero());
        stmt.setString(13, leitor.getEndereco().getUf());
        stmt.setInt(14, leitor.getId());
                
        numeroLinhasAfetadas = stmt.executeUpdate();
        
        stmt.close();
        conn.close();
            
        } catch (SQLException e) {

            System.out.println(e);
        }
        return numeroLinhasAfetadas;
        
        }
            
        public Leitor obterPorId (int id) {
            
            Leitor leitor = new Leitor();
            
            try {

                    StringBuilder str = new StringBuilder();
                    
                    str.append("select      a.id_leitor                      ");
                    str.append("           ,a.nome_leitor                    ");
                    str.append("           ,a.telefone                       ");
                    str.append("           ,a.email                          ");
                    str.append("           ,a.documento                      ");                      
                    str.append("           ,a.dataNascimento                 ");                   
                    str.append("           ,a.numero_cadastro                ");
                    str.append("           ,a.logradouro                     ");
                    str.append("           ,a.bairro                         ");
                    str.append("           ,a.cep                            ");
                    str.append("           ,a.cidade                         ");
                    str.append("           ,a.complemento                    ");
                    str.append("           ,a.numero                         ");
                    str.append("           ,a.uf                             ");
                    str.append(" from leitor a                               ");
                    str.append(" where a.id_leitor = ?                       ");
                                
            Connection conn = new ConnectionFactory().getConnection();            
                    
            PreparedStatement stmt = conn.prepareStatement(str.toString());
                        
            stmt.setInt(1, id);
            
           
            ResultSet rs = stmt.executeQuery();            
           
            while (rs.next()) {
                
                //Leitor leitor = new Leitor();
                
                leitor.setId(rs.getInt("id_leitor"));
                leitor.setNome(rs.getString("nome_leitor"));
                leitor.setTelefone(rs.getString("telefone"));
                leitor.setEmail(rs.getString("email"));
                leitor.setDocumento(rs.getString("documento"));                
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("dataNascimento"));
                leitor.setDataNascimento(calendar);
                
                leitor.setNumCadastro(rs.getString("numero_cadastro"));                
                
                Endereco end = new Endereco();
                
                end.setLogradouro(rs.getString("logradouro"));
                end.setNumero(rs.getString("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setUf(rs.getString("uf"));
                end.setComplemento(rs.getString("complemento"));                
               
                leitor.setEndereco(end);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return leitor;
    }
    
}
