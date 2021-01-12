package com.example.matisse.model;

import com.chg.ultimateprovider.Model;
import com.example.matisse.ResourceTable;
import com.example.matisse.viewholder.AlbumViewHolder;
import ohos.media.image.PixelMap;
import ohos.utils.net.Uri;

public class UriModel implements Model {

    private Uri uri;
    PixelMap pixelMap;
    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

   public UriModel(Uri muri,PixelMap pixelMap1){
       this.uri=muri;
       this.pixelMap=pixelMap1;
    }

    public PixelMap getPixelMap() {
        return pixelMap;
    }

    public void setPixelMap(PixelMap pixelMap) {
        this.pixelMap = pixelMap;
    }
    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_imagelayout;
    }

    @Override
    public Class getHolderClass(int position) {
        return AlbumViewHolder.class;
    }
}
