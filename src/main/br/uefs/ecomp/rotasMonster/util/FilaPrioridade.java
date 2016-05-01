
/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Bruno Menezes de Lima e Alyson Felipe Oliveira Dantas
 * Data:  06/04/2016
 *
 * Declaro que este código foi elaborado por nós de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor,
 * tais como provindos de livros e apostilas, e páginas ou documentos
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */

package br.uefs.ecomp.rotasMonster.util;

public class FilaPrioridade implements IFila{

	private CelulaPrioridade primeiro; //primeira posição da fila
	private CelulaPrioridade ultimo; //último elemento da fila
	private int tamanho = 0 ; //contador de elementos

	/**
	 * Construtor da classe Fila
	 */
	public FilaPrioridade() {
		this.primeiro = null;           //Inicializa o primeiro elemento como nulo
		this.ultimo = this.primeiro;    //e iguala o último ao primeiro
		this.tamanho = 0;        //além de inicializar o contador como 0    
	}

	@Override
	public boolean estaVazia() { //método para informar se a fila está vazia
		if (this.primeiro == null) { //se o primeiro elemento for nulo
			return true;
		}
		return false;
	}

	@Override
	public int obterTamanho() { //método para obter o número de elementos inseridos na fila
		return tamanho;
	}

	@Override
	public void inserirFinal(Object o) { //método para inserir elementos na fila
		CelulaPrioridade no = new CelulaPrioridade(this.ultimo, null, o); //Cria um novo nó usando o último da lista como anterior, nulo como próximo e com o objeto o como conteúdo
		if (!estaVazia()) {
			this.ultimo.setProximo(no); //diz que o próximo do último da lista é o novo nó
			this.ultimo = no; //aponta a última posição para esse novo nó
			tamanho++;
		} else {
			this.primeiro = no; //aponta o primeiro da fila para o novo nó
			this.ultimo = this.primeiro; //como só tem 1 elemento, o último se torna o primeiro
			tamanho++;
		}
	}

	/**
	 *  Metodo para inserir na fila de maneira ordenada
	 * @param chave
	 * @param o
	 */
	public void inserir(double chave, Object o) { //método para inserir elementos na fila
		CelulaPrioridade no = new CelulaPrioridade(chave, o); //Cria um novo nó usando o último da lista como anterior, nulo como próximo e com o objeto o como conteúdo
		if (!estaVazia()) {
			CelulaPrioridade aux = this.ultimo; //cria um auxiliar para percorrer a fila inicializado como o último
			if(tamanho == 1) {
				if(aux.getChave() > no.getChave()) {
					no.setProximo(aux);
					aux.setAnterior(no);
					this.primeiro = no;
					tamanho ++;
					return;
				}
				else {
					aux.setProximo(no);
					no.setAnterior(aux);
					this.primeiro = aux;
					this.ultimo = no;
					tamanho ++;
					return;
				}
			}
			while(aux.getChave() > no.getChave()) { //enquanto a chave do elemento for menor do que a do elemento a ser inserido
				aux = aux.getAnterior(); //avança uma posição da lista
				if (aux == this.primeiro && aux.getChave() > no.getChave()) { //caso tenha chegado ao início da lista, insere no início
					this.primeiro.setAnterior(no); //aponta o anterior do primeiro da lista para o novo nó
					no.setProximo(this.primeiro); //aponta o próximo do novo nó para o primeiro da lista 
					this.primeiro = no; //passa a posição de primeiro para o novo nó
					tamanho ++;
					return;
				}
				else if (aux == this.primeiro) {
					aux.getProximo().setAnterior(no);
					no.setProximo(aux.getProximo());
					no.setAnterior(aux);
					aux.setProximo(no);
					tamanho ++;
					return;
				}
			} //ao encontrar a posição correta do auxiliar
			if(aux == this.ultimo) {
				this.ultimo = no;
				no.setProximo(null); //aponta o próximo do novo nó para o próximo do auxiliar
				aux.setProximo(no); //aponta o próximo do auxiliar agora para o novo nó
				no.setAnterior(aux); //aponta o anterior do novo nó para o auxiliar
			}
			else {
				no.setProximo(aux.getProximo()); //aponta o próximo do novo nó para o próximo do auxiliar
				aux.getProximo().setAnterior(no);
				aux.setProximo(no); //aponta o próximo do auxiliar agora para o novo nó
				no.setAnterior(aux); //aponta o anterior do novo nó para o auxiliar
			}
			tamanho ++;
			return;

		} else { //caso a fila esteja vazia
			this.primeiro = no; //aponta o primeiro da fila para o novo nó
			this.ultimo = this.primeiro; //como só tem 1 elemento, o último se torna o primeiro
			tamanho++;
		}
	}

	@Override
	public Object removerInicio() { //método para remover elementos da fila
		if (tamanho == 1) { //se só existir um elemento na fila
			CelulaPrioridade aux = this.primeiro; //cria uma cópia do elemento a ser removido pra retorno
			this.primeiro = this.primeiro.getProximo(); //o primeiro agora aponta pra o próximo do primeiro elemento
			tamanho--;
			return aux; //retorna a cópia do elemento removido
		} else if (!estaVazia()) { 
			CelulaPrioridade aux = this.primeiro; //cópia do elemento a ser removido
			this.primeiro = this.primeiro.getProximo();     //a cabeça da fila se torna o próximo elemento
			this.primeiro.setAnterior(null); //direciona o anterior da nova cabeça pra nulo
			tamanho--;
			return aux; //retorna a cópia
		} else {
			return null;
		}
	}

	@Override
	public Object recuperarInicio() { //método para retornar o elemento na frente da fila
		return this.primeiro;
	}

	/**
	 * Metodo para limpar a fila
	 */
	public void limpar() {
		this.primeiro = null;
		this.ultimo = null;
		tamanho = 0;
	}

	/**
	 * Metodo para recuperar a ultima CelulaPrioridade
	 * @return ultimo
	 */
	public CelulaPrioridade recuperarFinal() {
		return this.ultimo;
	}

	@Override
	public MeuIterador iterador() { //método para criar um iterador com o início da fila
		return new MeuIterador(this.primeiro); //retorna um iterador com a cabeça da fila

	}

	/**
	 * Metodo para recuperar a primeira CelulaPrioridade
	 * @return
	 */
	public CelulaPrioridade getPrimeiro() {
		return this.primeiro;
	}

	/**
	 * Metodo para recuperar a ultima CelulaPrioridade
	 * @return ultimo
	 */
	public CelulaPrioridade getUltimo() {
		return this.ultimo;
	}
}



///////////////////////////////////////// Classe antiga //////////////////////////////////////////////



/*public Fila(){
		primeiro = null; //inicializa o primeiro e o último como nulos
		ultimo = null;
	}

    @Override
    public boolean estaVazia() { //método para verificar se a lista está vazia
    	if(primeiro == null){ //caso a primeira posição esteja vazia
    		return true; //retorne true
    	} //caso contrário
        return false; //retorne false
    }

    @Override
    public int obterTamanho() { //método pra obter o número de elementos da fila
        return tamanho; //retorna o contador da classe
    }

    @Override
    public void inserirFinal(Object o) { //método para inserir elementos na fila
    	CelulaPrioridade CelulaPrioridadeNova=new CelulaPrioridade(o); //cria uma nova célula chamada "CelulaPrioridadeNova" com o objeto recebido pelo método
        if (primeiro==null) { //caso a primeira posição esteja vazia
            primeiro=CelulaPrioridadeNova; //atribui a primeira posição da fila à célula nova
            ultimo=primeiro; //aponta a referência do último para o primeiro, já que só tem 1 elemento na fila
        }
        else { //caso já tenham elementos cadastrados
            ultimo.setProximo(CelulaPrioridadeNova); //aponta a referência do próximo do último elemento da lista para a nova célula criada
            ultimo=CelulaPrioridadeNova; //define a nova célula como último elemento 
        }
        tamanho ++; //acresce um no contador de elementos
    }

    @Override
    public Object removerInicio() { //método para remover um elemento da fila
        CelulaPrioridade auxiliar = null; //cria uma CelulaPrioridade para auxiliar nas remoções e inicializa ela como nulo
        if(primeiro!=null) { //caso existam elementos cadastrados na fila
            auxiliar = primeiro; //copia o primeiro elemento no auxiliar
            if(primeiro == ultimo) { //caso só exista um elemento cadastrado na fila
            	auxiliar=primeiro; //copia o primeiro elemento no auxiliar
                primeiro = null; //deleta o primeiro elemento
                ultimo = null; //deleta o último elemento
            } else { //caso tenha mais de um elemento na fila
            	auxiliar=primeiro; //copia o primeiro no auxiliar
                primeiro = primeiro.getProximo(); //passa a primeira posição para o segundo elemento da fila
            }
            tamanho--; //decresce um do contador de elementos
        }
        return auxiliar.getObjeto(); //retorna a cópia do elemento removido
    }

    @Override
    public Object recuperarInicio() { //método para retornar o primeiro elemento da fila
        return primeiro.getObjeto(); //retorna o objeto existente dentro do primeiro elemento da fila
    }

    @Override
    public Iterador iterador() { //método para criar um iterador da fila
        MeuIterador iterador = new MeuIterador(primeiro); //cria um novo iterador passando o primeiro elemento da fila como parâmetro
        return iterador; //retorna o iterador criado
    }

}*/
