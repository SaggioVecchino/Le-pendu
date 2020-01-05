package Model;


public class InscripException extends Exception {
    private boolean existeDeja;

    public boolean isExisteDeja() {
        return existeDeja;
    }

    public InscripException(boolean existeDeja)
    {
        this.existeDeja=existeDeja;
    }
}
