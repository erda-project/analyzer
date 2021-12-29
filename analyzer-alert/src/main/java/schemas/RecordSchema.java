package schemas;

import cloud.erda.analyzer.common.utils.JsonMapperUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RecordSchema<T> implements DeserializationSchema<T>, SerializationSchema<T> {

    private final static Logger logger = LoggerFactory.getLogger(RecordSchema.class);

    private final TypeInformation<T> type;

    public RecordSchema(Class<T> type) {
        Preconditions.checkNotNull(type, "type");
        this.type = TypeInformation.of(type);
    }


    @Override
    public T deserialize(byte[] bytes) throws IOException {
        try {
            return JsonMapperUtils.toObject(bytes, this.type.getTypeClass());
        } catch (Throwable throwable) {
            logger.error("Deserialize record fail. \nSource : {} \n", new String(bytes), throwable);
            return null;
        }
    }

    @Override
    public boolean isEndOfStream(T record) {
        return false;
    }

    @Override
    public byte[] serialize(T record) {
        try {
            return JsonMapperUtils.toBytes(record);
        } catch (IOException throwable) {
            logger.error("Serialize record fail. \n", throwable);
            return null;
        }
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(this.type.getTypeClass());
    }
}
