package eu.faircode.email;

/*
    This file is part of FairEmail.

    FairEmail is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    NetGuard is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NetGuard.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2018 by Marcel Bokhorst (M66B)
*/

import android.content.Context;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = EntityLog.TABLE_NAME,
        foreignKeys = {
        },
        indices = {
                @Index(value = {"time"})
        }
)
public class EntityLog {
    static final String TABLE_NAME = "log";

    @PrimaryKey(autoGenerate = true)
    public Long id;
    @NonNull
    public Long time;
    @NonNull
    public String data;

    static void log(Context context, String data) {
        EntityLog entry = new EntityLog();
        entry.time = new Date().getTime();
        entry.data = data;
        DB.getInstance(context).log().insertLog(entry);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityLog) {
            EntityLog other = (EntityLog) obj;
            return (this.time.equals(other.time) && this.data.equals(other.data));
        } else
            return false;
    }
}