package Dance;

public class Dancer {
    private String name;
    private int Age;
    private int WorkExp;

    @Override
    public String toString() {
        return "Dancer{" +
                "name='" + name + '\'' +
                ", Age=" + Age +
                ", WorkExp=" + WorkExp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dancer dancer = (Dancer) o;

        if (Age != dancer.Age) return false;
        if (WorkExp != dancer.WorkExp) return false;
        return name.equals(dancer.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getWorkExp() {
        return WorkExp;
    }

    public void setWorkExp(int workExp) {
        WorkExp = workExp;
    }
}
