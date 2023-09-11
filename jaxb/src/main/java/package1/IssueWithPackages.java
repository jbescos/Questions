/*
 * Copyright (c) 2023 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package package1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "issueWithPackages")
public class IssueWithPackages {

    private Holder holder;
    // Same name than Holder#addressInformation. The idea is to force in the XML 1+ times 'addressInformation'
    private package2.AddressInformation addressInformation;
    public Holder getHolder() {
        return holder;
    }
    public void setHolder(Holder holder) {
        this.holder = holder;
    }
    public package2.AddressInformation getAddressInformation() {
        return addressInformation;
    }
    public void setAddressInformation(package2.AddressInformation addressInformation) {
        this.addressInformation = addressInformation;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "holder")
    public static class Holder {
        private package1.AddressInformation addressInformation;
        public package1.AddressInformation getAddressInformation() {
            return addressInformation;
        }
        public void setAddressInformation(package1.AddressInformation addressInformation) {
            this.addressInformation = addressInformation;
        }
    }
}
