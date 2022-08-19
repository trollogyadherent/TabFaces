package trollogyadherent.tabfaces.registries;

import trollogyadherent.tabfaces.TabFaces;
import trollogyadherent.tabfaces.util.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServerRegistry {
    private ArrayList<ServerRegistry.Data> dataList;

    public ServerRegistry() {
        this.dataList = new ArrayList<>();
    }

    private class Data {
        String displayName;
        byte[] skinBytes;

        Data (String displayName, byte[] skinBytes) {
            this.displayName = displayName;
            this.skinBytes = skinBytes;
        }
    }

    Data getDataByDisplayName(String displayName) {
        for (Data data : this.dataList) {
            if (data.displayName.equals(displayName)) {
                return data;
            }
        }
        return null;
    }

    public void insert(String displayName, byte[] skinBytes) {
        if (getDataByDisplayName(displayName) == null) {
            this.dataList.add(new Data(displayName, skinBytes));
        } else {
            getDataByDisplayName(displayName).skinBytes = skinBytes;
        }
    }

    public void removeByDisplayName(String displayName) {
        Data data = getDataByDisplayName(displayName);
        if (data != null) {
            this.dataList.remove(data);
        }
    }

    public byte[] getAllDataAsBytes() {
        /* Structure: |4 bytes: number of elements|4 bytes: len of displayname|displayname|4 bytes: len of skin|skin| ... |...|*/
        byte[] res = new byte[0];
        for (Data data : dataList) {
            byte[] displayNameBytes = data.displayName.getBytes(StandardCharsets.UTF_8);
            try {
                res = Util.concatByteArrays(res, Util.prependLengthToByteArray(displayNameBytes));
                res = Util.concatByteArrays(res, Util.prependLengthToByteArray(data.skinBytes));
            } catch (IOException e) {
                TabFaces.error("Failed to concatenate byte arrays");
                e.printStackTrace();
            }
        }
        res = Util.prependLengthToByteArray(res, dataList.size());
        return res;
    }
}
