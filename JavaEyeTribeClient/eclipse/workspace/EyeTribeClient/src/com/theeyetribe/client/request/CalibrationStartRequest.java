/*
 * Copyright (c) 2013-present, The Eye Tribe. 
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the LICENSE file in the root directory of this source tree. 
 *
 */

package com.theeyetribe.client.request;

import com.theeyetribe.client.Protocol;

public class CalibrationStartRequest extends RequestBase
{
    public class Values
    {
        public Integer pointcount;
    }

    public Values values = new Values();

    public CalibrationStartRequest()
    {
        this.category = Protocol.CATEGORY_CALIBRATION;
        this.request = Protocol.CALIBRATION_REQUEST_START;
    }
}
