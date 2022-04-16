package excecao;

/** Excecao customizada para problemas de xadrez. */
public class ChessException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ChessException(String mensagem) {
    super(mensagem);
  }
}
