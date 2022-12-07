package edu.val.ejemploslibroandroidapp.tema6;

//BEAN, POJO, DTO

import android.os.Parcel;
import android.os.Parcelable;

public class InfoFormulario implements Parcelable {

    private String nombre;
    private int edad;
    private boolean hombre;
    private boolean mujer;
    private boolean mayoredad;

    public InfoFormulario(String nombre, int edad, boolean hombre, boolean mujer, boolean mayoredad) {
        this.nombre = nombre;
        this.edad = edad;
        this.hombre = hombre;
        this.mujer = mujer;
        this.mayoredad = mayoredad;
    }

    protected InfoFormulario(Parcel in) {
        nombre = in.readString();
        edad = in.readInt();
        hombre = in.readByte() != 0;
        mujer = in.readByte() != 0;
        mayoredad = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeByte((byte) (hombre ? 1 : 0));
        dest.writeByte((byte) (mujer ? 1 : 0));
        dest.writeByte((byte) (mayoredad ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InfoFormulario> CREATOR = new Creator<InfoFormulario>() {
        @Override
        public InfoFormulario createFromParcel(Parcel in) {
            return new InfoFormulario(in);
        }

        @Override
        public InfoFormulario[] newArray(int size) {
            return new InfoFormulario[size];
        }
    };

    @Override
    public String toString() {
        return "InfoFormulario{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", hombre=" + hombre +
                ", mujer=" + mujer +
                ", mayoredad=" + mayoredad +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isHombre() {
        return hombre;
    }

    public void setHombre(boolean hombre) {
        this.hombre = hombre;
    }

    public boolean isMujer() {
        return mujer;
    }

    public void setMujer(boolean mujer) {
        this.mujer = mujer;
    }

    public boolean isMayoredad() {
        return mayoredad;
    }

    public void setMayoredad(boolean mayoredad) {
        this.mayoredad = mayoredad;
    }
}
