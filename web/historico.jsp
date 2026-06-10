<%-- 
    Document   : historico
    Created on : 13 de abr. de 2026, 21:54:29
    Author     : PICHAU
--%>


<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Histórico de Movimentações</title>
        <style>
            /* Adicione aqui o mesmo CSS da sua index para manter o padrão */
            body { font-family: sans-serif; background-color: #112340; color: white; padding: 20px; }
            table { width: 100%; border-collapse: collapse; background: white; color: black; }
            th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
            th { background-color: #708090; color: white; }
            .btn-voltar { display: inline-block; margin-top: 20px; color: #17a2b8; text-decoration: none; font-weight: bold; }
        </style>
    </head>
    <body>
        <h1>Histórico de Movimentações</h1>

        <table>
            <thead>
                <tr>
                    <th>ID Produto</th>
                    <th>Nome</th>
                    <th>Tipo</th>
                    <th>Qtd</th>
                    <th>Valor Unitário</th> <th>Valor Total</th>   <th>Data/Hora</th>
                    <th>Observação</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listaItensMovimentados}">
                    <tr>
                        <td>${item.lote.produto.id}</td>
                        <td>${item.lote.produto.descricao}</td>
                        <td>${item.movimento.tipo}</td>
                        <td>${item.quantidade}</td>
                        <td>R$ ${item.valorItem}</td>

                        <td><strong>R$ ${item.valorTotal}</strong></td>

                        <td>${item.movimento.dataHora}</td>
                        <td>${item.movimento.observacao}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="index.html" class="btn-voltar">← Voltar para a Home</a>
    </body>
</html>
