<%-- 
    Document   : resultadoconsultartodos
    Created on : 12 de abr. de 2026, 18:17:36
    Author     : PICHAU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Necessário adicionar a biblioteca JSTL, deve estar disponivel pelo netbeans ja, sem necessidade de instalar por fora -->
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Estoque</title>
        <style>
            body { 
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
                background-color: #708090; 
                margin: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
            }
            table { width: 80%; border-collapse: collapse; margin: 20px auto; }
            th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
            th { background-color: #f4f4f4; }
            tr { background-color: #f4f4f4; border: 3px solid #000000 }
        </style>
    </head>
    <body>
        <h1 style="text-align: center;">Produtos em Estoque</h1>
        
        <table>
            <thead>
                <tr>
                    <th>ID do Produto</th>
                    <th>ID do Lote</th>
                    <th>Produto</th>
                    <th>Categoria</th>
                    <th>Qtd Atual</th>
                    <th>Qtd Mínima</th>
                    <th>Preco</th>
                    <th>Data de Validade</th>
                    <th>Última Atualização</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${llote}">
                    <tr>
                        <td>${item.produto.id}</td>
                        <td>${item.id}</td>
                        <td>${item.produto.descricao}</td>
                        <td>${item.produto.categoria}</td>
                        <td>${item.qtdLote}</td>                      
                        <td>${item.produto.qtdMinima}</td>
                        <td>${item.preco}</td>    
                        <td>${item.dataValidade}</td>
                        <td>${item.dataAtualizacao}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <center><a href="index.html">Voltar</a></center>
    </body>
</html>
