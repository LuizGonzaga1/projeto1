/**
 * Confirmação de exclus~~ao de um produto @flavio
 */
  
 function confirmar (id){
	let resposta = confirm("Confirma a exclusão deste produto?")
	if (resposta === true){
		//alert(id)
		window.location.href = "delete?id=" + id //é usado para fazer um redirecionamento. passo2 14#
	}
}