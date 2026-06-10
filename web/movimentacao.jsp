<%-- 
    Document   : movimentacao
    Created on : 13 de abr. de 2026, 20:29:13
    Author     : PICHAU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Movimentação</title>
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
            .form-container {
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                width: 100%;
                max-width: 400px;
            }
            table { width: 80%; border-collapse: collapse; margin: 20px auto; }
            th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
            th { background-color: #f4f4f4; }
            tr { background-color: #f4f4f4; border: 3px solid #000000 }
        </style>
    <link rel="stylesheet" href="style.css"> </head>
<body>
    <h2>Movimentação de Estoque</h2>
    <div class="form-container">
        <form action="controller" method="POST">
            <p>ID do Produto: <input type="number" name="idProduto" required></p>

            <p>Tipo: 
                <select name="selTipo">
                    <option value="ENTRADA">Entrada (+)</option>
                    <option value="SAIDA">Saída (-)</option>
                </select>
            </p>

            <p>Quantidade: <input type="number" name="quantidade" required></p>
            <p>Observação: <input type="text" name="obs"></p>

            <button type="submit" name="btnop" value="RegistrarMovimento">Confirmar</button>

            <a href="index.html">Cancelar</a>
        </form>
    </div>
</body>
</html>
