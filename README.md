
Requisitos

JAVA 1.8.
IDE STS 4
SPRING BOOT
SPRING SECURITY
HIBERNATE
FLYWAY CORE
Mysql
Tom cat 8 (se usar o STS, java incluso)
jpa Model Gen


Entrar no diretorio dos aquivos abaixo  e executar python -m SimpleHTTPServer para startar sevidor simples do python

<html>
  <body>
    <button onclick="enviar()">Pegar Token</button>
    <script>
	      function printResponse(){
	      console.log(this.responseText);	
        }
        function enviar(){

          var req = new XMLHttpRequest();
          req.addEventListener("load", printResponse);
          req.open("POST", "http://localhost:8080/oauth/token");
          req.setRequestHeader("Authorization", "Basic YW5ndWxhcjpAbmd1bEByMA==");
          req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          req.send("client_id=angular&username=admin&password=admin&grant_type=password");
        }
    </script>
</body>
</html> 


<html>
    <body>
        <button onclick="buscar()">Buscar Categorias</button>
        <script>
	          function printResponse(){
	          console.log(this.responseText);	
          }
             function buscar(){
              var req = new XMLHttpRequest();
              req.addEventListener("load", printResponse);
              req.open("GET", "http://localhost:8080/professores");
              req.send();
          }
        </script>
</body>
</html>

