package com.example.harmonyapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "label")
public class Label {

        @Id
        private String name;
        private long idGroup;

        public Label() {

        }

        public Label(String name, long idGroup) {
            this.name = name;
            this.idGroup = idGroup;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getIdGroup() {
            return this.idGroup;
        }

        public void setIdGroup(long idGroup) {
            this.idGroup = idGroup;
        }
}
