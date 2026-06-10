<%-- 
    Document   : resultadoconsultarbyid
    Created on : 12 de abr. de 2026, 19:16:29
    Author     : PICHAU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1 style="text-align: center;">Consulta de estoque</h1>
                
        <table>
            <thead>
                <tr>
                    <th>ID Produto</th>
                    <th>ID Estoque</th>
                    <th>Produto</th>
                    <th>Categoria</th>
                    <th>Qtd Atual</th>
                    <th>Qtd Mínima</th>
                    <th>Preço da unidade</th>
                    <th>Última Atualização</th>
                </tr>
            </thead>
            <tbody>
                <%-- Se 'prod' existir, exibe a linha --%>
                <c:if lote="${not empty lote}">
                    <tr>
                        <td>${lote.produto.id}</td>
                        <td>${lote.id}</td>
                        <td>${lote.produto.descricao}</td>
                        <td>${lote.produto.categoria}</td>
                        <td>${lote.qtdLote}</td>
                        <td>${lote.produto.qtdMinima}</td>
                        <td>R$ ${lote.preco}</td>
                        <td>${lote.dataAtualizacao}</td>
                    </tr>
                </c:if>    
            </tbody>
        </table>
        <br>
        <center><a href="index.html" style="color: white; font-weight: bold;">Voltar</a></center>
    </body>
</html>
