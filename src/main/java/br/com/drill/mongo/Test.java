/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drill.mongo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author jjunior
 */
public class Test {

    public static void main(String[] args) {

        try {
            Class.forName("org.apache.drill.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:drill:drillbit=localhost");

            String query = "select CATP_DS_CADASTRO, CADA_DS_ENDERECO_COMPLETO from mongo.giex.TB_ANALISE_DIVIDAS_DIM01 limit 1000";

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int total_rows = resultSet.getMetaData().getColumnCount();

                        System.out.println(resultSet.getString(1));
                        for (int i = 1; i <= total_rows; i++) {
                            System.out.println(resultSet.getObject(i));

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
