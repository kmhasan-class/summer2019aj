package bd.edu.seu.springbootdemo.exception;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String resource) {
        super(resource + " already exists!");
    }
}
