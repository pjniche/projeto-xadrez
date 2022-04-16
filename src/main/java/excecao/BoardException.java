package excecao;

/** Excecao customizada para problemas com o tabuleiro. */
public class BoardException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public BoardException(String mensagem) {
    super(mensagem);
  }
}
