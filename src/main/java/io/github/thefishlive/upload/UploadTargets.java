package io.github.thefishlive.upload;

import io.github.thefishlive.upload.gist.GistUploadTarget;
import io.github.thefishlive.upload.pastebin.PastebinUploadTarget;

import java.lang.reflect.Constructor;

public enum UploadTargets {

    GIST(GistUploadTarget.class),
    PASTEBIN(PastebinUploadTarget.class);

    private final Class<? extends UploadTarget> clazz;
    private Constructor<? extends UploadTarget> ctor;

    @java.beans.ConstructorProperties({"clazz"})
    private UploadTargets(Class<? extends UploadTarget> clazz) {
        this.clazz = clazz;
    }

    private Constructor<? extends UploadTarget> getCtor() throws NoSuchMethodException {
        if (ctor == null) {
            ctor = clazz.getConstructor();
        }

        return ctor;
    }

    public UploadTarget newHandler() throws ReflectiveOperationException {
        return getCtor().newInstance();
    }
}
