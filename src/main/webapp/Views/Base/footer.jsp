<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.model.VO.Config.ConfigVO"
%>

<%
	List<ConfigVO> datosEmpresa = (List<ConfigVO>) request.getAttribute("datosEmpresa");
%>

<footer class="py-3 bg-dark">

    <div class="container">
    	<p class="m-3 text-center text-white">Copyright &copy; Tienda Serbatic 2024</p>
    	<p class="m-3 text-center text-white">Desarrollado por: <a href="https://github.com/manuchidev/Tienda_Manuel_Chillon_Prieto"  class="linkGithub" target="_blank">Manuel Chillón Prieto</a></p>    
    </div>
    
    <div class="d-flex justify-content-around">
   	<%  
   	for (ConfigVO dato : datosEmpresa) {
   		
   		if (dato.getClave().equals("email") || dato.getClave().equals("direccion")) {
   	%>    	    
    	   <p class="m-2 text-center text-white"><%= dato.getValor() %></p>
   	<% 		
   		}
   	  } 
   	%>
   	</div>
   	
</footer>