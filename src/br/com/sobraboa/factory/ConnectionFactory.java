package br.com.sobraboa.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/SobraBoa?useTimezone=true&serverTimezone=UTC", 
                "root", 
                "15@Melo15" // substitua pela sua senha
            );
        } catch (Exception erro) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + erro.getMessage());
        }
    }
}