package coursework.operations;

public interface Notifiable {
    public boolean showConfirmation(String title, String head, String content);
    public void showAlertWithoutHeaderText(String title, String head);
    public void showAlertWithoutWarning(String title, String head);
}
