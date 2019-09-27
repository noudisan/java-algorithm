package com.ztt.algorithm.stack;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列
 *
 */
public class DogCatQueue {

    class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> dogC;
    private long count;

    public DogCatQueue() {
        dogQ = new LinkedList<>();
        dogC = new LinkedList<>();
        count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.dogC.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("unknown pet type");
        }
    }

    public Pet poolAll() {
        if (!dogQ.isEmpty() && !dogC.isEmpty()) {
            if (this.dogQ.peek().getCount() < this.dogC.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.dogC.poll().getPet();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll().getPet();
        } else if (!this.dogC.isEmpty()) {
            return this.dogC.poll().getPet();
        } else {
            throw new RuntimeException("queue is empty");
        }
    }

    public Dog pollDog() {
        if (!isDogQueueEmpty()) {
            return (Dog) this.dogQ.poll().getPet();
        } else {
            throw new RuntimeException("dog is empty");
        }
    }

    public Cat pollCat() {
        if (!isCatQueueEmpty()) {
            return (Cat) this.dogC.poll().getPet();
        } else {
            throw new RuntimeException("cat is empty");
        }
    }

    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.dogC.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return this.dogQ.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.dogC.isEmpty();
    }
}
