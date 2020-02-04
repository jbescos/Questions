package com.example.jsonb;

import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

public class SimpleAnnotatedDateContainer {
    
  @JsonbDateFormat(value = "EEE, dd MMM yyyy", locale = "de")
  private Date instance = new Date(0);

  public Date getInstance() {
    return instance;
  }

  public void setInstance(Date instance) {
    this.instance = instance;
  }
}
