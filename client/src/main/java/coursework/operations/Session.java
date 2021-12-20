package coursework.operations;

import coursework.models.Access;
import coursework.models.Product;
import coursework.models.ProductType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Session {

    private static Session instance;

    private AnchorPane anchorView;
    private AnchorPane anchorPage;
    private AnchorPane anchorMenu;

    private AnchorPane baseAnchor;

    private AnchorPane test;

    public AnchorPane getBaseAnchor() {
        return baseAnchor;
    }

    public void setBaseAnchor(AnchorPane baseAnchor) {
        this.baseAnchor = baseAnchor;
    }

    private Button buttonHome;
    private Button buttonLearn;

    private int roleId;
    private int userTotal;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private Access access;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;
    private ProductType productType;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    private Session() { }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public AnchorPane getAnchorView() { return anchorView; }
    public void setAnchorView(AnchorPane anchorView) { this.anchorView = anchorView; }

    public void setAnchorMenu(AnchorPane anchorMenu) {
        this.anchorMenu = anchorMenu;
    }

    public AnchorPane getAnchorMenu() {
        return anchorMenu;
    }

    public AnchorPane getAnchorPage() {
        return anchorPage;
    }

    public void setAnchorPage(AnchorPane anchorPage) {
        this.anchorPage = anchorPage;
    }

    public void setButtonHome(Button buttonHome) {
        this.buttonHome = buttonHome;
    }

    public void getButtonLearn() {

        buttonHome.setStyle("-fx-text-fill: black; -fx-background-radius: 15; -fx-background-color: transparent;");
        buttonLearn.setStyle("-fx-background-color:  #6495ED; -fx-text-fill: white; -fx-background-radius: 15");
    }

    public void setButtonLearn(Button buttonLearn) {
        this.buttonLearn = buttonLearn;
    }

    public AnchorPane getTest() {
        return test;
    }

    public void setTest(AnchorPane test) {
        this.test = test;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }
}
