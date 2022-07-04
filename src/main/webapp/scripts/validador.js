/**
 * Validação de formulário
 */

function validar() {

	/**
  		alert('teste')
	*/

	let codigo = frmProduto.codigo.value
	/**
  		Criamos uma variável chamada nome que irá receber do formulário "frmProduto" do campo "nome", o "valor".
	*/

	let nome = frmProduto.nome.value
	
	let categoria = frmProduto.categoria.value
	
	let valor = frmProduto.valor.value

	let quantidade = frmProduto.quantidade.value
	
	if (codigo === ""){
		alert('Preencha o campo Codigo')
		frmProduto.codigo.focus()
		return false
		
	}	else if (nome === ""){
			alert('Preencha o campo Nome')
			frmProduto.nome.focus()
			return false
		}	
		else if (categoria === ""){
			alert('Preencha o campo Categoria')
			frmProduto.categoria.focus()
			return false
		}	
		else if (valor === ""){
			alert('Preencha o campo Valor')
			frmProduto.valor.focus()
			return false
		}	
		else if (quantidade === ""){
			alert('Preencha o campo Quantidade')
			frmProduto.quantidade.focus()
			return false
		}	
		else{
			document.forms["frmProduto"].submit()
		}

}