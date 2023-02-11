public class FireTruck {
    private int id;
    private boolean status;

    public FireTruck(){
        idFireTrucks id = new idFireTrucks();
        this.id = id.getId();
        this.status=true;
    }

    public int getId(){
        return id;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean free){
        status = free;
    }
}
