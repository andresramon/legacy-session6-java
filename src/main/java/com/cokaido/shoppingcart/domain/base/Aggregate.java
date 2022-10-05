package com.cokaido.shoppingcart.domain.base;

public abstract class Aggregate<DataType> {
    public Id id;

    public Aggregate(Id id) {
        this.id = id;
    }

    public abstract DataType toData();

    public abstract void restore(DataType data);

    private boolean equals(Aggregate<DataType> other)
    {
        return toData()== other.toData();
    }
}
